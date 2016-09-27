package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResGroupActivityinfoToGameClientMessage;
	import net.Handler;

	public class ResGroupActivityinfoToGameClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGroupActivityinfoToGameClientMessage = ResGroupActivityinfoToGameClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}