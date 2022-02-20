var a = document.getElementsByClassName('kutija').length
console.log(a)
if (a / 4 <= 1) {
    document.getElementById("container").style.height = "370px";
} else if(a / 4 > 1 && a / 4 <=2) {
    document.getElementById("container").style.height = "740px";
} else if(a / 4 > 2 && a / 4 <=3) {
    document.getElementById("container").style.height = "1110px";
}else if(a / 4 > 3 && a / 4 <=4) {
    document.getElementById("container").style.height = "1470px";
}else if(a / 4 > 4 && a / 4 <=5) {
    document.getElementById("container").style.height = "1840px";
}else if(a / 4 > 5 && a / 4 <=6) {
    document.getElementById("container").style.height = "2210px";
}


