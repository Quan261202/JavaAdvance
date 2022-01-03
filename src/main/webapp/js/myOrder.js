//const li = document.querySelectorAll('ul li');
$(document).ready(() => {
    const li = $('.main-right ul li');
    for (let i = 0; i < li.length; ++i) {
        $(li[i]).on('click', (e) => {
            e.preventDefault();
            for (let j = 0; j < li.length; ++j) {
                $(li[j]).css({
                    'border-bottom': 'none'
                })
            }
            $(li[i]).css({
                'border-bottom': '2px solid red',
            })
            $.ajax({
                method: 'POST',
                url: "MyOrder",
                data: {status: i},
                success: (data) => {
                    $('.product').html(data)
                    // const res = $.parseJSON(data)
                    // let html = ``;
                    // for (let x in res) {
                    //     console.log(res[x])
                    //     let tong = 0;
                    //     for (let j = 0; j < res[x].length; ++j) {
                    //         html += `<div class="product-item">
					// 					<img src="${res[x][j].urlImage}" alt="">
					// 					<div class="product-detail">
					// 						<p>${res[x][j].productName}</p>
					// 						<span>x${res[x][j].quantity}</span>
					// 					</div>
					// 					<p class="price">${res[x][j].quantity * res[x][j].price}</p>
					// 				 </div>`
                    //         tong += res[x][j].quantity * res[x][j].price;
                    //     }
                    //     html += `<div class="pay">
                    //                 <span><img src="icons/gross.png" alt="">
                    //                     Tổngsố tiền: <span>$ </span><span>${tong}</span>
                    //                 </span>
                    //                 <div class="pay-function">
                    //
                    //                 </div>
                    //              </div>`
                    //     console.log(html)
                    // }
                }
            })
        })
    }
})

