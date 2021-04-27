function hostsss(host){ //双击切换主结点
    $.ajax({
        type: "POST",
        url: "/queryAccessByHost",
        data: {
            host:host
        },
        dataType: "json",
        success: function(data) {
            document.getElementById("container").innerHTML="";
            //[{"name":"FROM_host_166.112.3.10_TO_host_116.112.3.13","subject":"166.112.3.10","object":"116.112.3.13","accessControl":{"name":"ACL4","control":"Deny"}}]
            links = []
            for(var i = 0;i < data.length;++i){
                links.push({
                    source: data[i].subject,
                    stype: data[i].stype,
                    target: data[i].object,
                    ttype: data[i].otype,
                    type: data[i].accessControl.control,
                    rela:data[i].accessControl.name
                });
            }
            //console.log(links);
            startKG(links);
        },
        error:function (err) {
            alert("error");
        }
    });

}

jQuery(document).ready(async function() {
    var accordionsMenu = $('.cd-accordion-menu');

    const arr = await new Promise((resolve, reject) => {
            fetch('/queryAllNetWork').then(function(data){
                return data.json();
            }).then(function(data){
                resolve(data)
            })
        })


    let root = ''
    arr.forEach(item => {
        root += `<li class="has-children">
		<input type="checkbox" name="${item}" id="${item}">
		<label for="${item}">${item}</label>
	</li>`
    })
    accordionsMenu.append(root)
    accordionsMenu.each(function() {
        var accordion = $(this);
        //detect change in the input[type="checkbox"] value
        accordion.on('change', 'input[type="checkbox"]', async function(e) {
            var checkbox = $(this);
            // console.log(e.target.nextElementSibling.innerText); // 后去host
            console.log(e.target);
            if (e.target.parentNode.children.length === 2) {
                $.ajax({
                    type: "POST",
                    url: "/queryHostByNetWork",
                    data: {
                        network:e.target.nextElementSibling.innerText
                    },
                    dataType: "json",
                    success: function(node) {
                        const oul = document.createElement('ul')

                        let domm = ''
                        node.forEach(item => {
                            domm += `<li k="op"><a href="#" onclick="hostsss(this.innerHTML)">${item}</a></li>`
                        })
                        oul.innerHTML = domm
                        e.target.parentNode.appendChild(oul);
                    },
                    error:function (err) {
                        alert("error");
                    }
                });

            }

            (checkbox.prop('checked')) ? checkbox.siblings('ul').attr('style', 'display:none;').slideDown(300): checkbox.siblings('ul').attr('style', 'display:block;').slideUp(300);
        });
    });
});