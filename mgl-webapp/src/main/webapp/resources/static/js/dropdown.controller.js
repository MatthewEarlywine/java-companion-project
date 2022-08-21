function showNameFilter(event) {
	event.stopPropagation();
	document.getElementById("nameFilterDropdown").classList.toggle("show");
}

function showGenreFilter(event) {
	event.stopPropagation();
	document.getElementById("genreFilterDropdown").classList.toggle("show");
}


// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		for (const element of dropdowns) {
			element.classList.remove("show");
		}
	}
}  