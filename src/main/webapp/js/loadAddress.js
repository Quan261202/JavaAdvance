$(document).ready(()=>{
    const addressDetail = $('#address-detail').val()
    if (addressDetail) {
        const arrays = addressDetail.split(',')
        let province
        let district
        let ward
        if (arrays.length == 3) {
            province = arrays[2].trim()
            district = arrays[1].trim()
            ward = arrays[0].trim()
        } else if (arrays.length == 4) {
            province = arrays[3].trim()
            district = arrays[2].trim()
            ward = arrays[1].trim()
        }
        loadAddress(province, 'province')
        loadAddress(district, 'district', {'province': province})
        loadAddress(ward, 'ward', {'district': district})
    }
    else {
        loadAddress(null, 'province')
    }

    async function loadAddress(type, tile, value = null) {
        let res
        if (value !== null) {
            res = await fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address', {
                method: "POST",
                body: JSON.stringify(value)
            })
        } else {
            res = await fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address')
        }
        const data = await res.json()
        const values = JSON.parse(data)
        let html = ``
        for (let i = 0; i < values.length; ++i) {
            if (values[i] === type) {
                html += `<option value="${values[i]}" selected="selected">${values[i]}</option>`
            } else {
                html += `<option value="${values[i]}">${values[i]}</option>`
            }
        }
        $('#' + tile).html(html)
    }

    function callAPI(tile, value, type) {
        fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address', {
            method: "POST",
            body: JSON.stringify(value)
        }).then(res => {
            return res.json()
        }).then(data => {
            let html = `<option value="Chọn ${type}">Chọn ${type}</option>`
            const arrays = JSON.parse(data)
            for (let i = 0; i < arrays.length; ++i) {
                html += `<option value="${arrays[i]}">${arrays[i]}</option>`
            }
            $('#' + tile).html(html)
        })
    }

    $('#province').on('change', () => {
        let province = $('#province').val().toString();
        callAPI('district', {'province': province}, 'Quận Huyện')
    })

    $('#district').on('change', () => {
        let district = $('#district').val().toString();
        callAPI('ward', {'district': district}, 'Phường Xã')
    })
})