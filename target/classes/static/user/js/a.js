function setTab(name, cursel) {
    // alert("con_"+name);
    links_len = document.getElementsByClassName("con_"+name).length;
    console.log(document.getElementsByClassName("con_" + name));
    if(name=="one"){
        links_len=links_len-1;
    }
    for (var i = 1; i <= links_len; i++) {
        var content = document.getElementById(name + i);
        var rightContent = document.getElementById("con_" + name + "_" + i);
        if (i == cursel) {
            content.className = "off";
            rightContent.style.display = "block";
        } 
        else {
            content.className = "";
            rightContent.style.display = "none";
        }
    }
}

function none() {
    document.getElementById("con_one_0").style.display = "none";
}

function block() {
    document.getElementById("con_one_0").style.display = "block";
}

function tan(){
    document.getElementById('zhezhao').style.display="block";
}
function hidder(){
    document.getElementById('zhezhao').style.display="none";
}