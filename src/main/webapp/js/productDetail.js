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
    $('#add').on('click', (e)=>{
        e.preventDefault()
        const amount = parseInt($('#amount').text())
        $('#amount').text(amount + 1)
    })

    // sub quantity
    $('#sub').on('click', (e)=>{
        e.preventDefault()
        const amount = parseInt($('#amount').text())
        if(amount > 1)
        {
            $('#amount').text(amount - 1)
        }
    })

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
        const type = count == 1 ? 'province': count == 2 ? 'district' : 'ward'
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
                            if(count == 1)
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
                            }else if(count == 2)
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
                            else if(count == 3)
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