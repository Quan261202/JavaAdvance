$(document).ready(() => {
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

    $('#saveProfile').on('click', (e) => {
        e.preventDefault();
        const file = $('#formFileSm')[0].files[0]
        const profileCustomer = $('#form').serializeArray()
        const object = {}
        $.each(profileCustomer, (index, value) => {
            console.log(index)
            object[value.name] = value.value
        })
        object['address'] = 'Số nhà ?, ' + $('#ward').val() + ', ' + $('#district').val() + ', ' + $('#province').val()
        if (file) {
            object['avatar'] = 'image/' + file.name
            let formData = new FormData()
            formData.append("file", file)
            const request = new XMLHttpRequest()
            request.responseType = 'json'
            request.addEventListener("load", () => {
                if (request.status === 200) {
                    $.ajax({
                        type: 'POST',
                        contentType: 'application/json',
                        url: 'profile',
                        data: JSON.stringify(object),
                        success: (data) => {
                            alert(data);
                            $('#avatar').attr('src', object['avatar'])
                        },
                        error: (error) => {
                            alert(error)
                        }
                    })
                }
            })
            request.open("POST", "fileUpload")
            request.send(formData)
        }
    })
})