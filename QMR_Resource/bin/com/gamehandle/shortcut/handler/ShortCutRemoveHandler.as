package com.game.shortcut.handler{

	import com.game.shortcut.message.ShortCutRemoveMessage;
	import net.Handler;

	public class ShortCutRemoveHandler extends Handler {
	
		public override function action(): void{
			var msg: ShortCutRemoveMessage = ShortCutRemoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}