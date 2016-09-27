package com.game.map.handler{

	import com.game.map.message.ResArmorChangeMessage;
	import net.Handler;

	public class ResArmorChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResArmorChangeMessage = ResArmorChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}