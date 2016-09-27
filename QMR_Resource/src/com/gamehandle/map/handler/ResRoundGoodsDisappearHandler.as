package com.game.map.handler{

	import com.game.map.message.ResRoundGoodsDisappearMessage;
	import net.Handler;

	public class ResRoundGoodsDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundGoodsDisappearMessage = ResRoundGoodsDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}