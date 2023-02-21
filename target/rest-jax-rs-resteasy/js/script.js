var table = document.getElementById("myTable");

function enviarData() {

	var accessToken = "abcdefghijklmnopqrstuvwxyz";
	var url = "http://localhost:8080/rest-jax-rs-resteasy/rest/persons/";

	var nameValue = document.getElementById("nameId").value;
	var lastNameValue = document.getElementById("lastNameId").value;
	var ageValue = +document.getElementById("ageId").value;
	var phoneValue = document.getElementById("phoneId").value;
	var emailValue = document.getElementById("emailId").value;

	$.ajax({
		type : 'POST',
		url : url,
		data : JSON.stringify({
			"name" : nameValue,
			"lastName" : lastNameValue,
			"age" : ageValue,
			"cellPhone" : phoneValue,
			"email" : emailValue
		}),
		error : function(e) {
			console.log(e);
		},
		dataType : "json",
		success : function(response) {

			// console.log(response);

			// Create an empty <tr> element and add it to the 1st position of
			// the table:
			var row = table.insertRow(0);

			// Insert new cells (<td> elements) at the 1st and 2nd position of
			// the "new" <tr> element:
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);

			cell1.className = "cell100 column1";
			cell2.className = "cell100 column2";
			cell3.className = "cell100 column3";
			cell4.className = "cell100 column4";
			cell5.className = "cell100 column5";

			// Add some text to the new cells:
			cell1.innerHTML = response.name;
			cell2.innerHTML = response.lastName;
			cell3.innerHTML = response.email;
			cell4.innerHTML = response.cellPhone;
			cell5.innerHTML = response.age;

		},
		contentType : "application/json"
	});
}