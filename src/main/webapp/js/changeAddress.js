
$(document).ready(function() {
	$('.change').on('click', (e) => {
		e.preventDefault();
		$('.changeAddress').css({
			'display': 'flex'
		});
	});

	function callAPI(tile, value, type) {
		fetch('http://localhost:8080/ShopingHalloween/api/address', {
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
		let province = $('.item.two .province').val().toString();
		callAPI('district', { 'province': province }, 'Quận Huyện')
	})

	$('#district').on('change', () => {
		let district = $('#district').val().toString();
		callAPI('ward', { 'district': district }, 'Phường Xã')
	})

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
				window.location.href = 'http://localhost:8080/ShopingHalloween/CartDetails'
			}
		});
	})
});

