$(document).ready(()=>{

    let count = 1
    let name
    let addressDetail = ''
    let address
    setInterval(()=>{
        address = $('#address span')
    }, 20)

    $('#icon').on('click', async ()=>{
        const res = await fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address')
        const data = await res.json()
        const arrays = JSON.parse(data)
        let html = ``
        for (let i = 0; i < arrays.length; ++i) {
            html += `<span>${arrays[i]}</span>`
        }
        $('#address').html(html)

        $('.ship-address-detail').toggleClass('active')
        const values = $('#address span')
        for(let i = 0; i < values.length; ++i)
        {
            $(values[i]).on('click', ()=>{
                const data = $(values[i]).text()
                name = data
                addressDetail += data + ', '
                const value = {'province': data}
                callAPI(value)
                count = 2
                setTimeout(()=>{
                    for(let i = 0; i < address.length; ++i)
                    {
                        $(address[i]).on('click', ()=>{
                            const data = $(address[i]).text()
                            name = data
                            addressDetail += data + ', '
                            callAPI({'district': data})
                            count = 3
                            setTimeout(()=>{
                                for(let i = 0; i < address.length; ++i)
                                {
                                    $(address[i]).on('click', ()=>{
                                        const data = $(address[i]).text()
                                        addressDetail += data
                                        updateAddress(addressDetail)
                                        count = 1
                                    })
                                }
                            }, 200)
                        })
                    }
                }, 200)
            })
        }
    })

    // chose province or district
    function callAPI(value) {
        fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address', {
            method: "POST",
            body: JSON.stringify(value)
        }).then(res => {
            return res.json()
        }).then(data => {
            const arrays = JSON.parse(data)
            let  html = ``
            for (let i = 0; i < arrays.length; ++i) {
                html += `<span>${arrays[i]}</span>`
            }
            $('#address').html(html)
        })
    }

    // add quantity
    const quantity = $('#amount')
    $('#add').on('click', (e)=>{
        e.preventDefault()
        const amount = parseInt(quantity.text())
        quantity.text(amount + 1)
    })

    // sub quantity
    $('#sub').on('click', (e)=>{
        e.preventDefault()
        const amount = parseInt(quantity.text())
        if(amount > 1)
        {
            quantity.text(amount - 1)
        }
    })

    // like
    const like = $('.reviews-detail-content div #like')
    for (let i = 0; i < like.length; ++i)
    {
        let isLike = false
        $(like[i]).on('click', (e)=>{
            e.preventDefault()
            isLike = !isLike
            const totalLike = $(`.reviews .reviews-detail:nth-child(${i + 3}) .total-like`)
            const operator = isLike ? '+' : '-'
            const id = $(`.reviews .reviews-detail:nth-child(${i + 3}) #ID`).val()
            const object = {
                'id': id,
                'operator': operator
            }
            $.ajax({
                method: 'PUT',
                url: 'api/reviews',
                data: JSON.stringify(object),
                success: ()=>{
                    if(isLike)
                    {
                        $(`.reviews .reviews-detail:nth-child(${i + 3}) .like`).css({
                            'color': '#ee4d2d'
                        })
                        if(totalLike.text().indexOf('H') >= 0)
                        {
                            totalLike.text('1')
                        }
                        else{
                            totalLike.text(parseInt(totalLike.text()) + 1)
                        }
                    }
                    else{
                        $(`.reviews .reviews-detail:nth-child(${i + 3}) .like`).css({
                            'color': '#ccc'
                        })
                        if(parseInt(totalLike.text()) < 2)
                        {
                            totalLike.text('? Hữu ích')
                        }else{
                            totalLike.text(parseInt(totalLike) - 1)
                        }
                    }
                }
            })
        })
    }

    function updateAddress(addressDetail)
    {
        const add = addressDetail.split(', ').reverse().join(', ')
        $('.address').text(add)
        $('.ship-address-detail').toggleClass('active')
        const customer = {
            'customerID': $('#customerID').val(),
            'address': add
        }
        $.ajax({
            method: 'POST',
            url: 'UpdateAddress',
            data: JSON.stringify(customer),
            success: (data)=>{
                alert(data)
                addressDetail = ''
            },
            error: err=>{
                alert(err)
            }
        })
    }

    // find address
    $('#search').on('keyup', ()=>{
        console.log(count)
        const data = $('#search').val()
        const type = count === 1 ? 'province': count === 2 ? 'district' : 'ward'
        const object = {
            'type': type,
            'key': data,
            'name': name
        }
        $.ajax({
            method: 'POST',
            url: 'api/address',
            data: JSON.stringify(object),
            success: data=>{
                const arrays = JSON.parse(data)
                let  html = ``
                for (let i = 0; i < arrays.length; ++i) {
                    html += `<span>${arrays[i]}</span>`
                }
                $('#address').html(html)
                setTimeout(()=>{
                    for(let i = 0; i < address.length; ++i)
                    {
                        $(address[i]).on('click', ()=>{
                            const data = $(address[i]).text()
                            name = data
                            if(count === 1)
                            {
                                addressDetail += data + ', '
                                callAPI({'province' : data})
                                setTimeout(()=>{
                                    for(let i = 0; i < address.length; ++i)
                                    {
                                        $(address[i]).on('click', ()=>{
                                            const data = $(address[i]).text()
                                            addressDetail += data + ', '
                                            name = data
                                            callAPI({'district': data})
                                            setTimeout(()=>{
                                                for(let i = 0; i < address.length; ++i)
                                                {
                                                    $(address[i]).on('click', ()=>{
                                                        const data = $(address[i]).text()
                                                        addressDetail += data
                                                        updateAddress(addressDetail)
                                                    })
                                                }
                                            }, 100)
                                        })
                                    }
                                }, 100)
                            }else if(count === 2)
                            {
                                addressDetail += data + ', '
                                callAPI({'district' : data})
                                setTimeout(()=>{
                                    for(let i = 0; i < address.length; ++i)
                                    {
                                        $(address[i]).on('click', ()=>{
                                            const value = $(address[i]).text()
                                            addressDetail += value
                                            updateAddress(addressDetail)
                                        })
                                    }
                                }, 100)
                            }
                            else if(count === 3)
                            {
                                addressDetail += data
                                updateAddress(addressDetail)
                            }
                            $('#search').val('')
                            count++
                            if(count > 3) count = 1
                        })
                    }
                }, 100)
            }
        })
    })
})