const customerForm = document.getElementById('customerForm');
const createCustomerButton = document.getElementById('createCustomerButton');
const customerMessage = document.getElementById('customerMessage');

createCustomerButton.addEventListener('click', async () => {
  const firstName = document.getElementById('firstName').value;
  const lastName = document.getElementById('lastName').value;
  const street = document.getElementById('street').value;
  const address = document.getElementById('address').value;
  const city = document.getElementById('city').value;
  const state = document.getElementById('state').value;
  const email = document.getElementById('email').value;
  const phone = document.getElementById('phone').value;
 

  try {
    const response = await fetch('http://localhost:8080/c/new?cmd=create', {
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

    const data = await response;

    if (data.status = 200) {
      // Customer record created successfully
      customerMessage.textContent = 'Customer record created successfully';
    }
    else{
        customerMessage.textContent= 'Something Went Wrong';
    }
  } catch (error) {
    console.error(error);
    customerMessage.textContent = 'Something went wrong.';
  
  }

});


