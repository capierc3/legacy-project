### Quality Policy
> Describe your Quality Policy in detail for this Sprint (remember what I ask you to do when I talk about the "In your Project" part in the lectures and what is mentioned after each assignment (in due course you will need to fill out all of them, check which ones are needed for each Deliverable). You should keep adding things to this file and adjusting your policy as you go.

**GitHub Workflow** (due Feb 2nd)
  > Each sprint will have a dedicated branch, pulled from the master branch.  It will be named 'sprintX' where X is the current sprint iteration number.
  > Each user story will have an independent branch pulled from 'sprintX' and be named 'usX' where X is the user story number generated from Taiga
  > If multiple developers are working on the same user story but different tasks, separate branches shall be generated (1 for each developer) and named 'usX_TaskName' where TaskName aligns with the task being worked
  > Upon the completion of a user story, the sprintX branch shall be merged into the usX branch and any conflicts shall be resolved.  Upon successful testing, a pull request will be generated to have the changes merged into sprintX branch.
  > At the completion of the sprint, the sprintX branch will be thoroughly tested.  Upon completion of testing, the branch will be merged into master by the appointed Git Master

  > Comments: All commit comments shall start with US:#-TASK:#, followed by a breief description of what the commit added or modified.  An example would be "US:123-TASK:456 Add init() method to Class.java"

  > Conflicts: All conflits are to be handled by the developer responsible for generating the pull request.

**Unit Tests Blackbox** (due start Sprint 2)
  > Your Blackbox testing policy 

 **Unit Tests Whitebox** (due Feb 25th)
  > Your Whitebox testing policy 

**Code Review** (due Feb 25th)
  > Your Code Review policy   

  > Include a checklist/questions list which every developer will need to fill out/answe when creating a Pull Request to the Dev branch. 

  > Include a checklist/question list which every reviewer will need to fill out/anser when conducting a review, this checklist (and the answers of course) need to be put into the Pull Request review.

**Static Analysis**  (due start Sprint 3)
  > Your Static Analysis policy   

**Continuous Integration**  (due start Sprint 3)
  > Your Continuous Integration policy
