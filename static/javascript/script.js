const carousel = document.querySelector('.carousel');
const leftBtn = document.querySelector('.left-btn');
const rightBtn = document.querySelector('.right-btn');

let scrollAmount = 0;

leftBtn.addEventListener('click', () => {
    carousel.scrollTo({
        top: 0,
        left: (scrollAmount -= 220),
        behavior: 'smooth'
    });
    if(scrollAmount < 0) {
        scrollAmount = 0;
    }
});

rightBtn.addEventListener('click', () => {
    if(scrollAmount <= carousel.scrollWidth - carousel.clientWidth) {
        carousel.scrollTo({
            top: 0,
            left: (scrollAmount += 220),
            behavior: 'smooth'
        });
    }
});
