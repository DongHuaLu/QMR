package com.game.map.handler{

	import com.game.map.message.ResSynMonsterPositionMessage;
	import net.Handler;

	public class ResSynMonsterPositionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSynMonsterPositionMessage = ResSynMonsterPositionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}