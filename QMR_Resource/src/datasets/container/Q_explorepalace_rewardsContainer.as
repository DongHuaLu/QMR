package datasets.container {
	import flash.utils.Dictionary;
	import datasets.bean.Q_explorepalace_rewards;

	import flash.utils.ByteArray;
	/** 
	 * @author ExcelUtil Auto Maker
	 * 
	 * @version 1.0.0
	 * 
	 * @since 2011-5-8
	 * 
	 * Q_explorepalace_rewards
	 */
	public class Q_explorepalace_rewardsContainer {
		
		private var _list:Vector.<Q_explorepalace_rewards> = new Vector.<Q_explorepalace_rewards>();
		
		private var _dict:Dictionary = new Dictionary();
		
		private var _version:int;
		
		public function Q_explorepalace_rewardsContainer(bytes: ByteArray){
			_version = bytes.readInt();
			var num:int = bytes.readInt();
			for (var i : int = 0; i < num; i++) {
				var bean:Q_explorepalace_rewards = new Q_explorepalace_rewards();
				bean.read(bytes);
				_list.push(bean);
				_dict[String(bean.q_id)] = bean;
			}
		}
		
		public function get list(): Vector.<Q_explorepalace_rewards>{
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
