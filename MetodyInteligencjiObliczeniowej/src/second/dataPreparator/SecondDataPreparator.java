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
		
		/*double[] inputs1 =  {100,  300};
		double[] exResults1 = {1,0,0};
		SecondModel model1 = new SecondModel("model1",exResults1,inputs1);
		
		double[] inputs2 =  {1,  1};
		double[] exResults2 = {1,0,0};
		SecondModel model2 = new SecondModel("model2",exResults2,inputs2);
		
		double[] inputs3 =  {-200,  -400};
		double[] exResults3 = {1,0,0};
		SecondModel model3 = new SecondModel("model3",exResults3,inputs3);
		
		double[] inputs11 =  {300,  500};
		double[] exResults11 = {0,1,0};
		SecondModel model11 = new SecondModel("model11",exResults11,inputs11);
		
		double[] inputs21 =  {3,  6};
		double[] exResults21 = {0,1,0};
		SecondModel model21 = new SecondModel("model21",exResults21,inputs21);
		
		double[] inputs31 =  {-500,  -700};
		double[] exResults31 = {0,1,0};
		SecondModel model31 = new SecondModel("model31",exResults31,inputs31);
		
		double[] inputs12 =  {600,  900};
		double[] exResults12 = {0,0,1};
		SecondModel model12 = new SecondModel("model12",exResults12,inputs12);
		
		double[] inputs22 =  {8,  9};
		double[] exResults22 = {0,0,1};
		SecondModel model22 = new SecondModel("model22",exResults22,inputs22);
		
		double[] inputs32 =  {-100,  -300};
		double[] exResults32 = {0,0,1};
		SecondModel model32 = new SecondModel("model32",exResults32,inputs32);
		
		return new SecondModel[]{model1,model2,model3, model11,model21,model31, model12,model22,model32};*/
	}
}
