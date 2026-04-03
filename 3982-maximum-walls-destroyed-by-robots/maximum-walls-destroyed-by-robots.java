record RobotData(int pos, int dist) {}
record FireData(int stopPos, int wallsCount) {}

class Solution {
    public int maxWalls(int[] robots, int[] distances, int[] walls) {
        RobotData[] robotsData = IntStream.range(0, robots.length)
            .mapToObj(i -> new RobotData(robots[i], distances[i]))
            .sorted(Comparator.comparingInt(RobotData::pos))
            .toArray(RobotData[]::new);
        
        Arrays.sort(walls);

        FireData[] dpFireLeft = new FireData[robots.length];
        FireData[] dpFireRight = new FireData[robots.length];

        dpFireLeft[0] = fireLeft(new FireData(Integer.MIN_VALUE, 0), 0, robotsData, walls);
        dpFireRight[0] = fireRight(new FireData(Integer.MIN_VALUE, 0), 0, robotsData, walls);

        for (int i = 1; i < robots.length; i++) {
            FireData fireLeft_1 = fireLeft(dpFireLeft[i-1], i, robotsData, walls);
            FireData fireLeft_2 = fireLeft(dpFireRight[i-1], i, robotsData, walls);
            dpFireLeft[i] = fireLeft_1.wallsCount() > fireLeft_2.wallsCount() ? fireLeft_1 : fireLeft_2;

            FireData fireRight_1 = fireRight(dpFireLeft[i-1], i, robotsData, walls);
            FireData fireRight_2 = fireRight(dpFireRight[i-1], i, robotsData, walls);
            dpFireRight[i] = fireRight_1.wallsCount() > fireRight_2.wallsCount() ? fireRight_1 : fireRight_2;
        }

        return dpFireLeft[dpFireLeft.length - 1].wallsCount() > dpFireRight[dpFireRight.length - 1].wallsCount()
            ? dpFireLeft[dpFireLeft.length - 1].wallsCount() : dpFireRight[dpFireRight.length - 1].wallsCount();
    }

    private FireData fireRight(FireData prevFireData, int robotIndex, RobotData[] robotsData, int[] walls) {
        int rightPos;
        if (robotIndex == robotsData.length - 1) {
            rightPos = robotsData[robotIndex].pos() + robotsData[robotIndex].dist();
        } else {
            rightPos = Math.min(robotsData[robotIndex].pos() + robotsData[robotIndex].dist(), robotsData[robotIndex + 1].pos() - 1);
        }

        int leftWallIndex = Arrays.binarySearch(walls, robotsData[robotIndex].pos());
        if (leftWallIndex < 0) {
            leftWallIndex = -(leftWallIndex + 1);
        }
        int rightWallIndex = Arrays.binarySearch(walls, rightPos);
        if (rightWallIndex < 0) {
            rightWallIndex = -(rightWallIndex + 1) - 1;
        }

        return new FireData(rightPos, prevFireData.wallsCount() + rightWallIndex - leftWallIndex + 1);
    }

    private FireData fireLeft(FireData prevFireData, int robotIndex, RobotData[] robotsData, int[] walls) {
        int leftPos;
        if (robotIndex == 0) {
            leftPos = robotsData[robotIndex].pos() - robotsData[robotIndex].dist();
        } else {
            leftPos = Math.max(robotsData[robotIndex].pos() - robotsData[robotIndex].dist(), prevFireData.stopPos() + 1);
        }

        int leftWallIndex = Arrays.binarySearch(walls, leftPos);
        if (leftWallIndex < 0) {
            leftWallIndex = -(leftWallIndex + 1);
        }
        int rightWallIndex = Arrays.binarySearch(walls, robotsData[robotIndex].pos());
        if (rightWallIndex < 0) {
            rightWallIndex = -(rightWallIndex + 1) - 1;
        }

        return new FireData(robotsData[robotIndex].pos(), prevFireData.wallsCount() + rightWallIndex - leftWallIndex + 1);
    }
}