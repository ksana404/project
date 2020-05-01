		//ajax post方法
//get 改为post 方法需要修改  1.方法名  2.url提交类型  3.post请求头  
		function doAjaxPost(url,callback) {

			//第一步:注册dom(html 元素)事件
			//第二步:创建XHR对象并注册监听函数
			var xml = new XMLHttpRequest();
			xml.onreadystatechange = function() { //callback
				//监听与服务端的通讯过程
				//判断  通讯结束  返回结果正常
				if (xml.readyState == 4 && xml.status == 200) {
					//console.log(xml.responseText);
					//对结果处理   json格式String 转化 json对象
					console.log("txt:"+xml.responseText)
					var result =JSON.parse(xml.responseText);
					console.log("result:"+result)
					
					//doHandleResponseResult(result);
					callback(result);
					
				}

			}
			//第三步:创建与服务端的连接
			xml.open("POST", url, true); //true表示异步
			xml.setRequestHeader("Context-Type","application/x-www-form-urlencoded");
			//第四步:发送异步请求实现与服务端的通讯
			xml.send(null); //get请求send方法不传参数
		}
	
		//ajax get方法
		function doAjaxGet(url,callback) {

			//第一步:注册dom(html 元素)事件
			//第二步:创建XHR对象并注册监听函数
			var xml = new XMLHttpRequest();
			xml.onreadystatechange = function() { //callback
				//监听与服务端的通讯过程
				//判断  通讯结束  返回结果正常
				if (xml.readyState == 4 && xml.status == 200) {
					//console.log(xml.responseText);
					//对结果处理   json格式String 转化 json对象
					console.log("txt:"+xml.responseText)
					var result =JSON.parse(xml.responseText);
					console.log("result:"+result)
					
					//doHandleResponseResult(result);
					callback(result);
					
				}

			}
			//第三步:创建与服务端的连接
			xml.open("GET", url, true); //true表示异步
			//第四步:发送异步请求实现与服务端的通讯
			xml.send(null); //get请求send方法不传参数
		}
		
		/////////==========下面不是ajax,只是一些共性方法的提取=========/////////////////////////
		//处理响应结果 表单追加方法
		//1.初始化页面日志信息 ()内是List<SysLog> records
		//  doSetTableBodyRows(result.data.records);
		
		function doSetTableBodyRows(result){
			console.log(result);
			//1.获得tbody 对象，清空原有内容
			var tBody = document.getElementById("tbodyId");
			tBody.innerHTML="";
			  //2.迭代result记录,并将记录呈现在tbody位置
			  for(var i=0;i<result.length;i++){
				//2.1构建tr对象
			var tr =document.createElement("tr");
			 //2.2构建td对象,并追加到tr中
			 doCreatedTds(tr,result[i]);
			 //2.3将tr对象追加到tbody中
			 tBody.appendChild(tr);
				  
			  }  
			
		}
		
		
		//创建 td tr 填充数据
		function doCreatedTds(tr,row){
		
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.id; 	//获取信息
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.username; 	//获取信息   用户名
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.operation; 	//获取信息   操作
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.method; 	//获取信息 请求方法
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.params; 	//获取信息  请求参数
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.ip; 	//获取信息  IP
			tr.appendChild(td); //追加
			
			var td =document.createElement("td"); 	//1构建td对象
			td.innerText = row.createdTime; 	//获取信息   执行时长
			tr.appendChild(td); //追加
			
		}
		
		