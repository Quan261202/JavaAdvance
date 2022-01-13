
$(document).ready(function(){
    $('.change').on('click', (e)=>{
        e.preventDefault();
        $('.changeAddress').css({
            'display' : 'flex'
        });	
    });
	$('.item.two .province').on('change', ()=>{
		let province = $('.item.two .province').val().toString();
		console.log(province);
		$.ajax({
			method: "POST",
			url: 'LoadAddress?a=district&province=' + province,
			success: (data)=>{
					$('.item.two .district').html(`${data}`);
			}
		});
	})
	$('.item.two .district').on('change', ()=>{
		let district = $('.item.two .district').val().toString();
		console.log(district);
		$.ajax({
			method: "POST",
			url: 'LoadAddress?a=ward&district=' + district,
			success: (data)=>{
					$('.item.two .ghost').html(`${data}`);
			}
		});
	})

	const addressDetails = $('.item.two .item-addressDetails');
	$(addressDetails).on('blur', ()=>{
		const proName = $('.item.two .province').val();
		const disName = $('.item.two .district').val();
		const wardName = $('.item.two .ghost').val();
		if(proName == null || disName == null || wardName == null)
		{
			// sử lý lỗi j đó trong đây
		}
		else{
			const addDetail = $(addressDetails).val() + wardName + ', ' + disName + ', ' + proName;
			$(addressDetails).val(`${addDetail}`);
		}
	})

	const cancelAddress = $('.btn .cancel-address');
	cancelAddress.on('click', (e)=>{
		e.preventDefault();
		$('.changeAddress').css({
			'display':'none'
		});
	});

	const updateAddress = $('.btn .update-address');
	$(updateAddress).on('click', (e)=>{
		e.preventDefault();
		const data = $('#form').serializeArray();
		const object = {}
		$.each(data, (index, value)=>{
			console.log(index)
			object[value.name] = value.value
		})
		$.ajax({
			type: "POST",
			url: 'UpdateAddress',
			data: JSON.stringify(object),
			success: (data) => {
				alert(data)
				window.location.href = 'http://localhost:8080/ShopingHalloween/LoadAddress'
			}
		});
	})
});

