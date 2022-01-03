
$(document).ready(function(){
    $('.change').on('click', (e)=>{
        e.preventDefault();
        $('.changeAddress').css({
            'display' : 'flex'
        });	
    });
	$('.item.two .province').on('change', ()=>{
		let province = $('.item.two .province').val().toString();
		console.log(province);
		$.ajax({
			method: "POST",
			url: 'LoadAddress?a=district&province=' + province,
			success: (data)=>{
					$('.item.two .district').html(`${data}`);
			}
		});
	})
	$('.item.two .district').on('change', ()=>{
		let district = $('.item.two .district').val().toString();
		console.log(district);
		$.ajax({
			method: "POST",
			url: 'LoadAddress?a=ward&district=' + district,
			success: (data)=>{
					$('.item.two .ghost').html(`${data}`);
			}
		});
	})

	const addressDetails = $('.item.two .item-addressDetails');
	$(addressDetails).on('blur', ()=>{
		const proName = $('.item.two .province').val();
		const disName = $('.item.two .district').val();
		const wardName = $('.item.two .ghost').val();
		if(proName == null || disName == null || wardName == null)
		{
			// sử lý lỗi j đó trong đây
		}
		else{
			const addDetail = $(addressDetails).val() + wardName + ', ' + disName + ', ' + proName;
			$(addressDetails).val(`${addDetail}`);
		}
	})

	const cancelAddress = $('.btn .cancel-address');
	cancelAddress.on('click', (e)=>{
		e.preventDefault();
		$('.changeAddress').css({
			'display':'none'
		});
	});

	const updateAddress = $('.btn .update-address');
	$(updateAddress).on('click', (e)=>{
		e.preventDefault();
		const add = $('#address').val()
		const name = $('.item.two .name').val()
		const phone = $('.item.two .phone').val();
		//const data = $('#form').serializeArray();
		console.log(add)
		$.ajax({
			method: "POST",
			url: 'UpdateAddress?name=' + name + '&phone=' + phone + '&address=' + add,
			success: (data) => {
				const customer = $.parseJSON(data);
				let html = `   		<div class="address-name">
                						<h3>${customer.name}</h3>
                						<p style="border: none !important;color: black;">Mặc định</p>
            						</div>
            						<p class="content-add">${customer.address}</p>
            						<p>Điện thoại: ${customer.phone}</p>
            						<a href="">Giao đến địa chỉ này</a>
            						<a class="change" href="">Sửa</a>`
				$('.container .address').html(html)
				$('.changeAddress').hide('slow', ()=>{
					alert('update success')
				});
			}
		});

	})
});

