const loginForm = document.getElementById('loginForm');
const loginButton = document.getElementById('loginButton');
const loginMessage = document.getElementById('loginMessage');

loginButton.addEventListener('click', async () => {
  const loginId = document.getElementById('loginId').value;
  const password = document.getElementById('password').value;
  console.log(loginId);
  try {
    const response = await fetch('http://localhost:8080/user/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin':'*',
        
      },
      body: JSON.stringify({
        login_id: loginId,
        password,
      }),
    });
    



    if (response.status == 200) {
      loginMessage.textContent = 'Login successful!';
      window.location.href = 'customer.html';
    } else {
      loginMessage.textContent = response.message;
    }
  } catch (error) {
    console.error(error);
    loginMessage.textContent = 'Something went wrong.';
  }
});
