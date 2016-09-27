package com.game.backpack.handler{

	import com.game.backpack.message.ResOpenCellResultMessage;
	import net.Handler;

	public class ResOpenCellResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenCellResultMessage = ResOpenCellResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}