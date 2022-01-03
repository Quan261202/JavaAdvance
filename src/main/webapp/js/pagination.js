$(document).ready(() => {

	const pagination = $('.pagination a');
    const start = parseInt($('.pagination a.start').text());
    const end = parseInt($('.pagination a.end').text());
    for (let i = 0; i < pagination.length; ++i) {
        $(pagination[i]).on('click', (e) => {
            e.preventDefault();
            let currentPage = 0;
            let page;
            if (i === 0) page = parseInt($(pagination[i + 1]).text()) - 1;
            else page = parseInt($(pagination[i]).text());
            for (let j = 0; j < pagination.length; ++j) {
                const classList = $(pagination[j]).attr('class');
                if (classList.indexOf('active') >= 0)
                    if (j > 0) currentPage = parseInt($(pagination[j]).text());
                $(pagination[j]).removeClass('active')
            }
            $(pagination[i]).addClass('active')
            if (i === 0) {
                if (currentPage > start) {
                    page = currentPage - 1;
                }
            } else if (i === pagination.length - 1) {
                if (currentPage < end) {
                    page = currentPage + 1;
                }
            }
            console.log(page)
            console.log(currentPage)
            $.ajax({
            	method: 'POST',
            	url: 'Pagination?page=' + page,
            	success: (data) => {
            		const products = $.parseJSON(data.substr(0, data.indexOf(']') + 1));
            		const paging = data.substr(data.indexOf(']') + 1);
            		let html = ``;
            		for (let k = 0; k < products.length; ++k) {
            			html += `<div class="item">
            							<div class="icons">
            								<a class="fas fa-shopping-cart addCart" data-id="${products[k].productID}"></a>
            								<a href="" class="fas fa-eye"></a>
            								<a href="" class="fas fa-share"></a>
            							</div>
            							<div class="image">
            								<img src="${products[k].urlImage}" alt="">
            							</div>
            							<div class="content">
            								<h3>${products[k].productName}</h3>
            								<h3>${products[k].price}<span> $49.99</span></h3>
            							</div>
            						</div>`;
            		}
            		$('div.items.product').html(html)
            		$('.pagination').html(paging);
            	}
            });
        })
    }
})