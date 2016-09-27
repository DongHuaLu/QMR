package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResGroupActivityinfoWoridToClientMessage;
	import net.Handler;

	public class ResGroupActivityinfoWoridToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGroupActivityinfoWoridToClientMessage = ResGroupActivityinfoWoridToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}