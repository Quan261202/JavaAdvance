
$(document).ready(function() {
	setInterval(() => {
		const listCart = $('.removeCart');
		for (let i = 0; i < listCart.length; ++i) {
			$(listCart[i]).on('click', (e) => {
				e.preventDefault();
				id = $(listCart[i]).data('id');
				$.ajax({
					method: "POST",
					url: 'removeCart?id=' + id,
					success: (data) => {
						const arraysObject = $.parseJSON(data);
						console.log(typeof arraysObject);
						$(listCart[i]).hide('slow', ()=>{
							let html = '';
							let total = 0;
							for(let j = 0; j < arraysObject.length; ++j)
							{
								html +=		` 		<div class="item">
															<div class="image">
																<img src="${arraysObject[j].urlImage}" alt="" width="80px" height="60px">
															</div>
															<div class="detail">
																<h3>${arraysObject[j].productName}</h3>
																<p class="prices">${arraysObject[j].quantity}:${arraysObject[j].quantity *  arraysObject[j].price}</p>
															</div>
															<a href="#" class="removeCart fas fa-times" data-id="${arraysObject[j].productID}"></a>
														</div>`;
								total += arraysObject[j].quantity *  arraysObject[j].price;
							}
							html += `	<h3 class="price">Total:<span>$${total}</span></h3>
										<a href="CartDetails" class="btn">Check Cart</a>`;
							$('.cart').html(html);
						});
					}
				});
			});
		}
	}, 1000);
	const listCarts = $('.addCart');
	for (let i = 0; i < listCarts.length; ++i) {
		$(listCarts[i]).on('click', (e) => {
			e.preventDefault();
			id = $(listCarts[i]).data('id');
			$.ajax({
				method: "POST",
				url: 'addCartServlet?id=' + id,
				success: (data) => {
					const arraysObject = $.parseJSON(data);
					console.log(typeof arraysObject);
					let html = '';
					let total = 0;
					for(let j = 0; j < arraysObject.length; ++j)
					{
						html +=		` 		<div class="item">
														<div class="image">
															<img src="${arraysObject[j].urlImage}" alt="" width="80px" height="60px">
														</div>
														<div class="detail">
															<h3>${arraysObject[j].productName}</h3>
															<p class="prices">${arraysObject[j].quantity}:${arraysObject[j].quantity *  arraysObject[j].price}</p>
														</div>
														<a href="#" class="removeCart fas fa-times" data-id="${arraysObject[j].productID}"></a>
													</div>`;
						total += arraysObject[j].quantity *  arraysObject[j].price;
					}
					html += `	<h3 class="price">Total:<span>$${total}</span></h3>
									<a href="CartDetails" class="btn">Check Cart</a>`;
					$('.cart').html(html);
					alert('add cart success');
				}
			});
		});
	}
});