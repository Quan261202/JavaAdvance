
$(document).ready(() => {
	const lists = $('.delete-product');
	for (let i = 0; i < lists.length; ++i) {
		$(lists[i]).click(() => {
			id = $(lists[i]).data('id');
			$.ajax({
				type: "GET",
				url: 'Controller?a=Delete&id=' + id,
				success: function(data) {
					$(lists[i]).closest('tr').hide('slow');
				}
			});
		});
	}
	$('.search .search-input').on('keyup', ()=>{
		let value = $('.search .search-input').val();
		const categoryID = $('.categoryID').val();
		console.log(categoryID);
		$.ajax({
			method: "POST",
			url: 'Controller?a=Search&categoryID=' + categoryID + '&key=' + value,
			success: function(data) {
				document.querySelector('#customers').innerHTML = data;; 
			}
		});
	});
});