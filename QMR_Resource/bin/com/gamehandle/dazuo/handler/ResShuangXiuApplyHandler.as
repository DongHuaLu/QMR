package com.game.dazuo.handler{

	import com.game.dazuo.message.ResShuangXiuApplyMessage;
	import net.Handler;

	public class ResShuangXiuApplyHandler extends Handler {
	
		public override function action(): void{
			var msg: ResShuangXiuApplyMessage = ResShuangXiuApplyMessage(this.message);
			//TODO 添加消息处理
		}
	}
}