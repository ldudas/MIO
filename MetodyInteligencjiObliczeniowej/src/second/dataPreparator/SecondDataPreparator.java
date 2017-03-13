package second.dataPreparator;

import second.model.SecondModel;

public class SecondDataPreparator {

	public static SecondModel[] prepareLearningData() {
		int[] inputs1 =  {60,  6};
		int[] exResults1 = {1,0,0};
		SecondModel model1 = new SecondModel("model1",exResults1,inputs1);
		
		int[] inputs2 =  {20,  1};
		int[] exResults2 = {0,1,0};
		SecondModel model2 = new SecondModel("model2",exResults2,inputs2);
		
		int[] inputs3 =  {1000,  6000};
		int[] exResults3 = {0,0,1};
		SecondModel model3 = new SecondModel("model3",exResults3,inputs3);
		
		return new SecondModel[]{model1,model2,model3};
	}
}
