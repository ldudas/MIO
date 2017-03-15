package second.dataPreparator;

import second.model.SecondModel;

public class SecondDataPreparator {

	public static SecondModel[] prepareLearningData() {
		double[] inputs1 =  {1000,  3};
		double[] exResults1 = {1,0,0};
		SecondModel model1 = new SecondModel("model1",exResults1,inputs1);
		
		double[] inputs2 =  {800,  10};
		double[] exResults2 = {0,1,0};
		SecondModel model2 = new SecondModel("model2",exResults2,inputs2);
		
		double[] inputs3 =  {700,  5};
		double[] exResults3 = {0,0,1};
		SecondModel model3 = new SecondModel("model3",exResults3,inputs3);
		
		return new SecondModel[]{model1,model2,model3};
	}
}
