package com.game.spirittree.handler{

	import com.game.spirittree.message.ResFruitTheftNumToClientMessage;
	import net.Handler;

	public class ResFruitTheftNumToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFruitTheftNumToClientMessage = ResFruitTheftNumToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}