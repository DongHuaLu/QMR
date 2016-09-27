package com.game.task.handler{

	import com.game.task.message.ResTaskGoldAddNumMessage;
	import net.Handler;

	public class ResTaskGoldAddNumHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTaskGoldAddNumMessage = ResTaskGoldAddNumMessage(this.message);
			//TODO 添加消息处理
		}
	}
}