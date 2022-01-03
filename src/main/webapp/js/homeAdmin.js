
const menu = document.querySelector('.home-item .home-item-icon');
const nav = document.querySelector('.nav');
const contents = document.querySelectorAll('.nav a .content');
const home = document.querySelector('.home');

const title = ['Admin', 'Home', 'Customer', 'Store', 'Message', 'Setting', 'Sign out']
let count = 0;
menu.onclick = (e)=>{
    e.preventDefault();
    nav.classList.toggle('active');
    home.classList.toggle('active');
    if(count % 2 == 0)
    {
        for(let i = 0; i < contents.length; ++i)
        {
            contents[i].innerText = '';
        }
    }
    else{
        for(let i = 0; i < contents.length; ++i)
        {
            contents[i].innerText = title[i];
        }
    }
    count++;
}