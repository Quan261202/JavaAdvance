
$(document).ready(function() {

	$('.change').on('click', async  (e) => {
		e.preventDefault();
		$('.changeAddress').css({
			'display': 'flex'
		});
		const address = $('#address-detail').text()
		if(address)
		{
			const arrays = address.split(',')
			let province
			let district
			let ward
			if(arrays.length === 3)
			{
				province = arrays[2].trim()
				district = arrays[1].trim()
				ward = arrays[0].trim()
			}else if(arrays.length === 4)
			{
				province = arrays[3].trim()
				district = arrays[2].trim()
				ward = arrays[1].trim()
			}
			await loadAddress(province, 'province')
			await loadAddress(district, 'district', { 'province': province })
			await loadAddress(ward, 'ward', { 'district': district })
		}
		else{
			await loadAddress(null, 'province')
		}
	});

	async function loadAddress(type, tile, value = null)
	{
		let res
		if(value !== null)
		{
			res = await fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address', {
				method: "POST",
				body: JSON.stringify(value)
			})
		}else{
			res = await fetch('http://localhost:6789/ShoppingHalloween_war_exploded/api/address')
		}
		const data = await res.json()
		const values = JSON.parse(data)
		let html = ``
		for(let i = 0; i < values.length; ++i)
		{
			if(values[i] === type)
			{
				html += `<option value="${values[i]}" selected="selected">${values[i]}</option>`
			}
			else{
				html += `<option value="${values[i]}">${values[i]}</option>`
			}
		}
		$('#' + tile).html(html)
	}

	const addressDetails = $('.item.two .item-addressDetails');
	$(addressDetails).on('blur', () => {
		const proName = $('.item.two .province').val();
		const disName = $('.item.two .district').val();
		const wardName = $('.item.two .ghost').val();
		if (proName == null || disName == null || wardName == null) {
			// sử lý lỗi j đó trong đây
		}
		else {
			const addDetail = $(addressDetails).val() + wardName + ', ' + disName + ', ' + proName;
			$(addressDetails).val(`${addDetail}`);
		}
	})

	const cancelAddress = $('.btn .cancel-address');
	cancelAddress.on('click', (e) => {
		e.preventDefault();
		$('.changeAddress').css({
			'display': 'none'
		});
	});

	const updateAddress = $('.btn .update-address');
	$(updateAddress).on('click', (e) => {
		e.preventDefault();
		const data = $('#form').serializeArray();
		const object = {}
		$.each(data, (index, value) => {
			console.log(index)
			object[value.name] = value.value
		})
		const json = JSON.stringify(object)
		console.log(json)
		$.ajax({
			type: "POST",
			url: 'UpdateAddress',
			contentType: 'application/json',
			data: json,
			success: (data) => {
				alert(data)
				window.location.href = 'http://localhost:6789/ShoppingHalloween_war_exploded/CartDetails'
			}
		});
	})
});

