package com.game.zones.handler{

	import com.game.zones.message.ResZoneBossAppearMessage;
	import net.Handler;

	public class ResZoneBossAppearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResZoneBossAppearMessage = ResZoneBossAppearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}