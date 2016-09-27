package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_public_ranking;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_public_ranking
	 */
	public class Q_public_rankingContainer {
		
		private var _list:Vector.<Q_public_ranking> = new Vector.<Q_public_ranking>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_public_rankingContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_public_ranking = new Q_public_ranking();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_range_min)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_public_ranking>{
			return _list;
		}
		
		public function get dict(): Dictionary{
			return _dict;
		}
		
		public function get version(): int{
			return _version;
		}
	}
}
