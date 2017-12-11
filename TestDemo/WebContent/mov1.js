function getByClass(aParent,sClass)
{
	var aEle=aParent.getElementsByTagName('*');
	var aResult=[];
	for(var i=0;i<aEle.length;i++)
	{
		if(aEle[i].className==sClass)
		{
			aResult.push(aEle[i])	
		}
	}
	return aResult;
}
function getStyle(obj,name)
{
	if(obj.currentStyle)
	{
		return obj.currentStyle[name];	
	}
	else
	{
		return getComputedStyle(obj,false)[name];	
	}
}
function startMove(obj,attr,iTarget,fnEnd)
{
	clearInterval(obj.timer);
	obj.timer=setInterval(function(){
		var cur=0;
		if(attr=='opacity')
		{
			cur=Math.round((getStyle(obj,attr))*100);	
		}
		else
		{
			cur=parseInt(getStyle(obj,attr))	
		}
		var speed=(iTarget-cur)/7;
		speed=speed>0?Math.ceil(speed):Math.floor(speed);
		if(cur==iTarget)
		{
			clearInterval(obj.timer);
			if(fnEnd)fnEnd();
		}
		else
		{	
			if(attr=='opacity')
			{
				obj.style.filter='alpha(opacity:'+(cur+speed)+')';
				obj.style.opacity=(cur+speed)/100;	
			}
			else
			{
				obj.style[attr]=cur+speed+'px';
			}
		}
	},30)	
}