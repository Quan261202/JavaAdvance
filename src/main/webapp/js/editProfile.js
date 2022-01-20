$(document).ready(async () => {

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
                        url: 'UpdateAddress',
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