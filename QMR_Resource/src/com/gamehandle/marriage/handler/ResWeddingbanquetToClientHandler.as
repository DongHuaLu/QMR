package com.game.marriage.handler{

	import com.game.marriage.message.ResWeddingbanquetToClientMessage;
	import net.Handler;

	public class ResWeddingbanquetToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWeddingbanquetToClientMessage = ResWeddingbanquetToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}