var a = document.getElementsByClassName('kutija').length
console.log(a)
if (a / 4 <= 1) {
    document.getElementById("container").style.height = "270px";
} else if(a / 4 > 1 && a / 4 <=2) {
    document.getElementById("container").style.height = "540px";
} else if(a / 4 > 2 && a / 4 <=3) {
    document.getElementById("container").style.height = "810px";
}else if(a / 4 > 3 && a / 4 <=4) {
    document.getElementById("container").style.height = "1080px";
}else if(a / 4 > 4 && a / 4 <=5) {
    document.getElementById("container").style.height = "1350px";
}else if(a / 4 > 5 && a / 4 <=6) {
    document.getElementById("container").style.height = "1620px";
}