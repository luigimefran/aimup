const menuBtn = document.getElementById('menuBtn');
const menu = document.getElementById('menu');
const blurOverlay = document.getElementById('blurOverlay');

menuBtn.addEventListener('click', () => {
  menu.classList.toggle('open');
  blurOverlay.classList.toggle('show');
});

blurOverlay.addEventListener('click', () => {
  menu.classList.remove('open');
  blurOverlay.classList.remove('show');
});
