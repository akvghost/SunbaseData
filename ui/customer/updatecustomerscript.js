const customerForm = document.getElementById('customerForm');
const createCustomerButton = document.getElementById('createCustomerButton');
const customerMessage = document.getElementById('customerMessage');

const fname = document.getElementById('firstName');
const lname = document.getElementById('lastName');
const st = document.getElementById('street');
const addr = document.getElementById('address');
const cityVal = document.getElementById('city');
const stateVal = document.getElementById('state');
const mail = document.getElementById('email');
const phn = document.getElementById('phone');






let jsonString = localStorage.getItem('jsonData');

window.addEventListener('load',async () =>{
  
  
    console.log(jsonString);
    try{
      const jsonObject = JSON.parse(jsonString);
       fname.value = jsonObject.first_name;
       lname.value = jsonObject.last_name;
       st.value = jsonObject.street;
       cityVal.value = jsonObject.city;
       stateVal.value = jsonObject.state;
       addr.value = jsonObject.address;
       mail.value = jsonObject.email;
       phn.value = jsonObject.last_name;
      // console.log(jsonObject);
     }
    catch (error) {
      console.error(error);
    }
  
});



updateCustomerButton.addEventListener('click', async () => {
  const firstName = document.getElementById('firstName').value;
  const lastName = document.getElementById('lastName').value;
  const street = document.getElementById('street').value;
  const address = document.getElementById('address').value;
  const city = document.getElementById('city').value;
  const state = document.getElementById('state').value;
  const email = document.getElementById('email').value;
  const phone = document.getElementById('phone').value;
 
 
    try {
      const jsonObject = JSON.parse(jsonString);
      console.log(jsonObject.uuid);
    const response = await fetch('http://localhost:8080/c/update?cmd=update&uuid='+jsonObject.uuid, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        first_name: firstName,
        last_name: lastName,
        street,
        address,
        city,
        state,
        email,
        phone,
      }),
    });
    localStorage.removeItem('jsonData');  
    window.alert("Update Successful,Redirecting to home");
    window.location.href = './customer.html';
  }
  catch(error){
    console.log(error);
  }
 

});


