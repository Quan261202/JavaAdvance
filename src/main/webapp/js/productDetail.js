$(document).ready(()=>{

    let address
    setInterval(()=>{
        address = $('#address span')
    }, 50)

    let addressDetail = ''
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
                addressDetail += data + ', '
                const value = {'province': data}
                callAPI(value)
                setTimeout(()=>{
                    for(let i = 0; i < address.length; ++i)
                    {
                        $(address[i]).on('click', ()=>{
                            const data = $(address[i]).text()
                            addressDetail += data + ', '
                            callAPI({'district': data})
                            setTimeout(()=>{
                                for(let i = 0; i < address.length; ++i)
                                {
                                    $(address[i]).on('click', ()=>{
                                        const data = $(address[i]).text()
                                        addressDetail += data
                                        const add = addressDetail.split(', ').reverse().join(', ')
                                        $('.address').text(add)
                                        $('.ship-address-detail').toggleClass('active')
                                        const object = {
                                            'customerID': $('#customerID').val(),
                                            'address': add
                                        }
                                        $.ajax({
                                            method: 'POST',
                                            url: 'UpdateAddress',
                                            data: JSON.stringify(object),
                                            success: (data)=>{
                                                alert(data)
                                            },
                                            error: err=>{
                                                alert(err)
                                            }
                                        })
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
})