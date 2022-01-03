const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
    container.classList.remove("sign-up-mode");
});

const error = document.querySelector('p.message');
if(error){
    const name = document.querySelector('#name')
    name.onfocus = ()=>{
        error.style.transition = 'all ease-in-out .2s'
        error.style.display = 'none'
    }
}