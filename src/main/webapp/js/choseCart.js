
$(document).ready(() => {
	//giá mua tối thiểu để đc giảm giá
	const freeOne = 39;
	const freeTwo = 100;
	let freeShip = 0;
	// khách hàng tuỳ chỉnh số lượng hàng
	const add = $('.amount .add');
	const sub = $('.amount .sub');
	const quantity = $('.amount .quantity');
	for (let i = 0; i < add.length; ++i) {
		let amount = $(quantity[i]).text();
		// check và xử lý khi tăng số lượng
		$(add[i]).on('click', () => {
			amount++;
			const id = $(add[i]).data('id');
			$(quantity[i]).text(`${amount}`);
			console.log(id);
			const price = parseInt($($('.right .prices')[i]).text());
			$.ajax({
				method: "PUT",
				url: 'CartAPI?amount=' + amount + '&id=' + id,
				success: (data) => {
					console.log(data);
					alert(data)
					$($('.right p.money')[i]).text(`${amount * price}`);
				}
			})
		});
		// check và xử lý khi giảm số lượng
		$(sub[i]).on('click', () => {
			amount--;
			if (amount < 0)
				amount = 0;
			const id = $(add[i]).data('id');
			$(quantity[i]).text(`${amount}`);
			const price = parseInt($($('.right .prices')[i]).text());
			console.log(id);
			$.ajax({
				method: "PUT",
				url: 'CartAPI?amount=' + amount + '&id=' + id,
				success: (data) => {
					alert(data)
					$($('.right p.money')[i]).text(`${amount * price}.0`);
				}
			})
		});
	}

	// kiểm tra chọn từng phần tử một tính tiền cần phải thanh toán
	const checkAllCarts = $('.container-item-two .check input');
	let total = 0;
	let count = 0;
	for (let i = 0; i < checkAllCarts.length; ++i) {
		$(checkAllCarts[i]).on('change', () => {
			$('.check.all input').prop('checked', false);
			if ($(checkAllCarts[i]).is(":checked")) {
				if (i == 1) {
					total += parseInt($($(`.listCartItems :nth-child(${i + 1}) p.money`)[1]).text());
				}
				else {
					total += parseInt($(`.listCartItems :nth-child(${i + 1}) p.money`).text());
				}
				count++;
				if (total > freeTwo) {
					freeShip = 5;
					$('.processbar .one').css({
						'width': '100%',
						'background-color': '#5ed084'
					})
					$('.pay-item .frees span').text(`-${freeShip}`);
				}
				else if (total > freeOne) {
					freeShip = 3;
					$('.processbar .one').css({
						'width': '50%',
						'background-color': '#5ed084'
					})
					$('.pay-item .frees span').text(`-${freeShip}`);
				}
				else {
					freeship = 0;
				}
				$('.processbar p span').text(`+ ${freeShip}$`);
				$('.btn .count').text(`${count}`);
				$('.pay .totalMoney').text(`${total}`);
				$('.pay .lastPrice').text(`${total - freeShip}$`);
			}
			else {
				if (i == 1) {
					total -= parseInt($($(`.listCartItems :nth-child(${i + 1}) p.money`)[1]).text());
				}
				else {
					total -= parseInt($(`.listCartItems :nth-child(${i + 1}) p.money`).text());
				}
				count--;
				if (total > freeTwo) {
					freeShip = 5;
					$('.processbar .one').css({
						'width': '100%',
						'background-color': '#5ed084'
					})
					$('.pay-item .frees span').text(`-${freeship}`);
				}
				else if (total > freeOne) {
					freeShip = 3;
					$('.processbar .one').css({
						'width': '50%',
						'background-color': '#5ed084'
					})
					$('.pay-item .frees span').text(`-${freeship}`);
				}
				else {
					freeShip = 0;
					$('.processbar .one').css({
						'width': '0%',
						'background-color': '#ccc'
					})
					$('.pay-item .frees span').text(`${freeShip}`);
				}
				$('.processbar p span').text(`+ ${freeShip}$`);
				$('.btn .count').text(`${count}`);
				$('.pay .totalMoney').text(`${total}`);
				$('.pay .lastPrice').text(`${total - freeShip}$`);
			}
			if (total == 0) {
				$('.pay .lastPrice').text(`Vui lòng chọn sản phẩm`);
			}
		});
	}
	// kiểm tra check tất cả cartItems tính toán số tiền cần trả
	$('.check.all input').on('change', () => {
		if ($('.check.all input').is(":checked")) {
			total = 0;
			count = checkAllCarts.length;
			const moneys = $(`.listCartItems p.money`);
			for (let i = 0; i < moneys.length; ++i) {
				total += parseInt($(moneys[i]).text());
			}
			for (let i = 0; i < checkAllCarts.length; ++i) {
				$(checkAllCarts[i]).prop('checked', true);
			}
			if (total > freeTwo) {
				freeShip = 5;
				$('.processbar .one').css({
					'width': '100%',
					'background-color': '#5ed084'
				})
				$('.pay-item .frees span').text(`-${freeShip}`);
				$('.processbar p span').text(`+ ${freeShip}$`);
			}
			else if (total > freeOne) {
				freeShip = 3;
				$('.processbar .one').css({
					'width': '50%',
					'background-color': '#5ed084'
				})
				$('.pay-item .frees span').text(`-${freeShip}`);
				$('.processbar p span').text(`+ ${freeShip}$`);
			}
			$('.btn .count').text(`${count}`);
			$('.pay .totalMoney').text(`${total}`);
			$('.pay .lastPrice').text(`${total - freeShip}$`);
		}
		else {
			for (let i = 0; i < checkAllCarts.length; ++i) {
				$(checkAllCarts[i]).prop('checked', false);
			}
			count = 0;
			$('.processbar .one').css({
				'width': '0%',
				'background-color': '#ccc'
			})
			total = 0;
			freeShip = 0;
			$('.processbar p span').text(`+ ${freeShip}$`);
			$('.pay-item .frees span').text(`${freeShip}`);
			$('.btn .count').text(`${count}`);
			$('.pay .totalMoney').text(`${total}`);
			$('.pay .lastPrice').text(`Vui lòng chọn sản phẩm`);
		}
	});

	//xử lý khi người dùng thanh toán Purchase(mua hàng)
	let cartItemsID = [];
	const purchase = $('div.btn a.btn-pay');
	$(purchase).on('mouseleave', () => {
		$('div.btn a.btn-pay').attr('href', 'PayMent?id=');
	})
	$(purchase).on('mouseover', (e) => {
		e.preventDefault();
		cartItemsID = [];// reset lại mảng id trước mỗi lần click
		//kiểm tra xem những phần tử nào đc check
		for (let i = 0; i < checkAllCarts.length; ++i) {
			if ($(checkAllCarts[i]).is(":checked")) {
				cartItemsID.push($(removeCartItem[i]).data('id'));
			}
		}
		if(cartItemsID.length > 0)
		{
			var href = $('div.btn a.btn-pay').attr('href');
			if(!/[0-9]/.test(href))
			{				
				href += cartItemsID; 
			}
			if(parseInt($('.pay-item .frees span').text().substring(1)) > 0)
			{
				href += '&free=';
				href += $('.pay-item .frees span').text().substring(1);
			}
			$('div.btn a.btn-pay').attr('href', href);
		}
	});

	let removeCartItem = $('.removeCart');

	$('.check.all span').text(`${removeCartItem.length}`);
	for (let i = 0; i < removeCartItem.length; ++i) {
		$(removeCartItem[i]).on('click', (e) => {
			e.preventDefault();
			const id = $(removeCartItem[i]).data('id');
			$.ajax({
				method: "DELETE",
				url: 'CartAPI?data=' + id,
				success: (data) => {
					alert(data);
					window.location.href = "/ShoppingHalloween_war_exploded/CartDetails";
				}
			})
		})
	}
});