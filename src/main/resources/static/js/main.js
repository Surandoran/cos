let flag = true;

const openNav = () => {
    if (flag == true){
      document.getElementById("mySidenav").style.width = "200px";
      document.getElementById("menu-list").style.display= 'flex';
      document.getElementById("menu-list").style.alignItems= 'center';
      document.getElementById("logo4").style.visibility= 'hidden';
      flag = false
    }else{
      document.getElementById("mySidenav").style.width = "0";
      document.getElementById("menu-list").style.display= 'none';
      document.getElementById("logo4").style.visibility= 'visible';
      flag = true
    }
  };
