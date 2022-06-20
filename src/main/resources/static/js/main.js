let navflag = true;


const openCate = () => {

}


const openNav = () => {
    if (navflag == true){
        document.getElementById("mySidenav").style.width = "200px";
        document.getElementById("menu-list").style.display= 'flex';
        document.getElementById("menu-list").style.alignItems= 'center';
        document.getElementById("logo4").style.visibility= 'hidden';
        navflag = false
    }else{
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("menu-list").style.display= 'none';
        document.getElementById("logo4").style.visibility= 'visible';
        navflag = true
    }
};
$(document).ready(function(){
$('.one-time').slick({
    dots: true,
    infinite: true,
    speed: 200,
    slidesToShow: 1,
    slidesToScroll:1,
    adaptiveHeight: true,
    vertical : false,
    prevArrow:"<button id=prev>&lt;</button>",
    nextArrow:"<button id=next>&gt;</button>",
    dots:true,
    dotsClass:'bn-controller' ,
});
$('.multiple-items').slick({
    infinite: true,
    slidesToShow: 2,
    speed: 1000,
    autoplaySpeed : 2000,
    arrows : false,   //prev,next버튼 표시여부
    autoplay: true,  //자동넘김 여부
    slidesToScroll: 3
});

})

//메인 스크롤 이벤트
$(document).ready(function() {
    let controller = new ScrollMagic.Controller();
    let animateElem = [".animate_1", ".animate_2", ".animate_3"];
    let triggerElem = [".trigger_1", ".trigger_2", ".trigger_3"];

    for (let i = 0; i < animateElem.length; i++) {
        let currentAnimateElem = animateElem[i];
        let currentTriggerElem = triggerElem[i];

        let timeline = new TimelineMax();

        let tween_move = TweenMax.fromTo(
            currentAnimateElem,
            1,
            {
                ease: SlowMo.ease.config(0.7, 0.7, false),
                y: 50
            },
            {
                ease: SlowMo.ease.config(0.7, 0.7, false),
                y: -50
            }
        );

        let tween_opacity = new TimelineMax();
        tween_opacity
            .to(currentAnimateElem, 0.3, {
                ease: Linear.easeNone,
                opacity: 1
            })
            .to(
                currentAnimateElem, 0.3, {
                    ease: Linear.easeNone,
                    opacity: 0
                },
                "+=0.4"
            );

        timeline.add(tween_move, 0).add(tween_opacity, 0);

        let scene_main = new ScrollMagic.Scene({
            triggerElement: currentTriggerElem,
            duration: "900px"
        })
            .setTween(timeline)
            .addTo(controller);
    }
    const video = document.getElementById("bgvid");

    $(window).scroll(function () {
        let y = $(window).scrollTop()
        console.log(y)
        if (y > 2000) {
            // $('video').css("display", "none")
            $('video').fadeOut();
        } else {
            $('video').fadeIn();
        }
    })
});