package com.game.spirittree.handler{

	import com.game.spirittree.message.ResRipeningDecYBToClientMessage;
	import net.Handler;

	public class ResRipeningDecYBToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRipeningDecYBToClientMessage = ResRipeningDecYBToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}