$(document).ready(()=>{
    const App = {
        itemOfPage: 3,
        pageRange: 3,
        currentPage: 1,
        offset: 0,
        totalPage: 0,
        loadProducts: function(data){
            $('#arrival .heading').text(data['name'])
            let html = ``
            for(let i = 0; i < data.length; ++i)
            {
                html += `<div class="item">
                            <div class="icons">
                                <a class="fas fa-shopping-cart addCart" data-toggle="tooltip" title="Thêm vào giỏ hàng!" data-id="${data[i].productID}"></a>
                                <a href="ProductDetail?productID=${data[i].productID}" class="fas fa-eye"></a>
                                <a href="" class="fas fa-share"></a>
                            </div>
                            <div class="image">
                                <img src="${data[i].urlImage}" alt="">
                            </div>
                            <div class="content">
                                <h3>${data[i].productName}</h3>
                                <h3>
                                    ${data[i].price}<span> $95.99</span>
                                </h3>
                            </div>
                        </div>`
            }
            $('.arrivals').html(html)
        },
        loadPagination: async function(category){
            const pagination = $('#pagination')
            let startPage
            let endPage
            if(this.totalPage === 0)
            {
                const data = await this.getData(category, true)
                this.totalPage = Math.ceil(data["products"].length / this.itemOfPage)
            }
            if(this.pageRange < this.totalPage)
            {
                startPage = this.currentPage - (this.pageRange - 1)/2;
                endPage = this.currentPage + (this.pageRange - 1)/2.0;
                if(endPage > this.totalPage) {
                    endPage = this.totalPage
                }
            }
            else{
                startPage = 1
                endPage = this.pageRange
            }
            if(Math.ceil((startPage)) === Math.ceil(endPage)) startPage -= 1
            let html = ``
            html += `<a class='previous'>&laquo;</a>`
            for(let i = Math.ceil(startPage); i <= Math.ceil(endPage); ++i)
            {
                if(this.currentPage === i)
                    html += `<a class='active'>${i}</a>`
                else html += `<a class=''>${i}</a>`
            }
            html += `<a class='next'>&raquo;</a>`
            $(pagination).html(html)
            await this.events(category)
        },
        getData: async function(category, all = false){
            let url = 'http://localhost:6789/ShoppingHalloween_war_exploded/ws/products/category/' + category
            if(all === false){
                url += '/' + this.offset
            }
            const res = await fetch(url)
            return res.json()
        },
        events: async function(category){
            const pagination = $('#pagination a')
            for(let i = 0; i < pagination.length; ++i)
            {
                $(pagination[i]).on('click', async ()=>{
                    const page = parseInt($(pagination[i]).text())
                    if(Number.isNaN(page))
                    {
                        if($(pagination[i]).attr('class') === 'next')
                        {
                            if(this.currentPage < this.totalPage)
                                this.currentPage += 1
                            else this.currentPage = 1
                        }
                        else{
                            if(this.currentPage > 1)
                                this.currentPage -= 1
                            else
                                this.currentPage = this.totalPage
                        }
                    }else{
                        if(this.currentPage !== page)
                        {
                            this.currentPage = page
                        }
                    }
                    this.offset = (this.currentPage - 1) * 3
                    const data = await this.getData(category, false)
                    this.loadProducts(data)
                    this.addProduct()
                    await this.loadPagination(category)
                })
            }
        },
        addProduct: ()=>{
            const listCarts = $('.addCart');
            for (let i = 0; i < listCarts.length; ++i) {
                $(listCarts[i]).on('click', (e) => {
                    e.preventDefault();
                    const id = $(listCarts[i]).data('id');
                    $.ajax({
                        method: "POST",
                        url: 'addCartServlet?id=' + id,
                        success: (data) => {
                            const arraysObject = $.parseJSON(data);
                            let html = '';
                            let total = 0;
                            for(let j = 0; j < arraysObject.length; ++j)
                            {
                                html +=		`<div class="item">
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
        },
        load: async function(category)
        {
            const data = await this.getData(category)
            this.loadProducts(data)
            this.addProduct()
            await this.loadPagination(category)
        }
    }
    App.load(3).then(()=>{
        console.log("App is running")
    })
})