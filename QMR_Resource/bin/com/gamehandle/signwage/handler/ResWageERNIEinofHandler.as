package com.game.signwage.handler{

	import com.game.signwage.message.ResWageERNIEinofMessage;
	import net.Handler;

	public class ResWageERNIEinofHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWageERNIEinofMessage = ResWageERNIEinofMessage(this.message);
			//TODO 添加消息处理
		}
	}
}