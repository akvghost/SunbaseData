 
const createbutton = document.getElementById('createCustomerButton');

createbutton.addEventListener('click', () => {
  window.location.href = './createcustomer.html';
});

const customerList = document.getElementById('customerList');
var uuidArray = [];
  var uuidIndex = -1;
window.addEventListener('load', async () => {
  // Fetch the customer list from the API
  
  try{
  const response = await fetch('http://localhost:8080/c/get?cmd=get_customer_list');
  
 
  var i=0;
  
    if(response.status !=200){
  console.log(response.status);
    window.location.href = './index.html';}
  
  const customers = await response.json();
 const customerCount = customers.length;
  // Display the customer list in the table
  for (const customer of customers) {
    const row = document.createElement('tr');
    console.log(customer.uuid);
    uuidArray.push(customer.uuid);
    const firstNameCell = document.createElement('td');
    firstNameCell.textContent = customer.first_name;
    row.appendChild(firstNameCell);

    const lastNameCell = document.createElement('td');
    lastNameCell.textContent = customer.last_name;
    row.appendChild(lastNameCell);

    const addressCell = document.createElement('td');
    addressCell.textContent = customer.address;
    row.appendChild(addressCell);

    const cityCell = document.createElement('td');
    cityCell.textContent = customer.city;
    row.appendChild(cityCell);

    const stateCell = document.createElement('td');
    stateCell.textContent = customer.state;
    row.appendChild(stateCell);

    const emailCell = document.createElement('td');
    emailCell.textContent = customer.email;
    row.appendChild(emailCell);

    const phoneCell = document.createElement('td');
    phoneCell.textContent = customer.phone;
    row.appendChild(phoneCell);
     const actionCell = document.createElement("td");
        actionCell.classList.add("text-center");

        const removeButton = document.createElement("button");
        removeButton.classList.add("btn", "btn-sm", "btn-danger");
        removeButton.id = "removeBtnId"+i;
        removeButton.textContent = "Remove";

        const editButton = document.createElement("button");
        editButton.id="editBtnId"+i;
        editButton.classList.add("btn", "btn-sm", "btn-primary");
        editButton.textContent = "Edit";

        actionCell.appendChild(removeButton);
        actionCell.appendChild(editButton);
    
       row.appendChild(actionCell);
       
    customerList.appendChild(row);
    i++;
     

  }

  for(let x = 0;x<customerCount;x++){
    const removeCustomer = document.getElementById('removeBtnId'+x);
 

console.log(removeCustomer.textContent);
removeCustomer.addEventListener('click',async () => {
//  window.location.href = './customer.html';
try {
  console.log(uuidArray[x]);
  const response = await fetch('http://localhost:8080/c/delete?cmd=delete&uuid='+uuidArray[x], {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      
    });

    window.location.href = './customer.html';
} catch (error) {
  console.log(error);
}
  
  
  console.log(uuidArray[x]);
});
  }
  
  for(let x = 0;x<customerCount;x++){
    const editCustomer = document.getElementById('editBtnId'+x);
    const jsonString = JSON.stringify(customers[x]);
 

console.log(editCustomer.textContent);
editCustomer.addEventListener('click', () => {
     localStorage.setItem('jsonData', jsonString);
    localStorage.setItem('isEdit',true);
 window.location.href = './updatecustomer.html';
  console.log(customers[x])
});
  }

}
  catch(error){
    console.log(error);
  }
 
});




