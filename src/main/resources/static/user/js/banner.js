window.onload=function(){
    var imglist=document.getElementById("imgList");
    var imgArr=document.getElementsByClassName("bannerP");
    imglist.style.width=1020*imgArr.length+"px";

    //设置按钮链接效果
    var index=0;
    var allA=document.getElementsByClassName("bannerA");
    allA[index].classList.add("active");

    // 点击超链接切换图片
    for(var i=0;i<allA.length;i++){
        allA[i].num=i;
        allA[i].onclick=function(){
            //关闭自动切换的定时器
            clearInterval(timer);
            index=this.num;
            //imglist.style.left=-1020*index+"px";
            //allA[index].style.backgroundColor="black";
            setA();

            move(imglist , "left" , -1020*index , 150 , function(){

            });
        }
    }

    //开启自动切换图片
    autoChange();

    //change color
    function setA(){
        //判断当前索引是否是最后一张图片
        if(index >= imgArr.length - 1){
            //则将index设置为0
            index = 0;
            
            //此时显示的最后一张图片，而最后一张图片和第一张是一摸一样
            //通过CSS将最后一张切换成第一张
            imgList.style.left = 0;
        }

        for(var i=0;i<allA.length;i++){
            allA[i].classList.remove("active");
        }
        allA[index].classList.add("active");
    }

    //定义一个自动切换的定时器的标识
    var timer;
    //创建一个函数，用来开启自动切换图片
    function autoChange(){
        
        //开启一个定时器，用来定时去切换图片
        timer = setInterval(function(){
            
            //使索引自增
            index++;
            
            //判断index的值
            index %= imgArr.length;
            
            //执行动画，切换图片
            move(imgList , "left" , -1020*index , 150 , function(){
                //修改导航按钮
                setA();
            });
            
        },5000);
        
    }
}