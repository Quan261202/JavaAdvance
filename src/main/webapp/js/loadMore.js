const logout = document.querySelector("#out");
logout.onclick = (e) => {
	e.preventDefault();
	document.querySelector(".dropdown").classList.toggle('active');
}

$(document).ready(()=>{
	setInterval(()=>{
		$.ajax({
			method: 'POST',
			url: 'loadProducts',
			success: (data)=>{
				if(parseInt(data) > 0)
				{
					$('#cart .message').text(data);
				}	
			}
		})
	}, 1000);
});